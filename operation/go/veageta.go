package main

import (
	"fmt"
	"github.com/google/uuid"
	vegeta "github.com/tsenart/vegeta/lib"
	"net/http"
	"os"
	"strings"
	"time"
)

func main() {
	rate := vegeta.Rate{Freq: 10, Per: time.Second} // edit the rate
	duration := 60 * time.Second                    // edit the duration

	targeter := NewCustomTargeter()
	attacker := vegeta.NewAttacker()
	var metrics vegeta.Metrics
	runDate := time.Now().Format(time.RFC3339)
	filename := "response_" + runDate + ".txt"
	f, _ := os.OpenFile(filename, os.O_APPEND|os.O_WRONLY|os.O_CREATE, 0644)
	for res := range attacker.Attack(targeter, rate, duration, "Load Test") {
		metrics.Add(res)
		f.WriteString(string(res.Body) + "\n")
	}
	metrics.Close()
	fmt.Printf("%+v  \n", metrics)
}

func NewCustomTargeter() vegeta.Targeter {
	return func(tgt *vegeta.Target) error {
		if tgt == nil {
			return vegeta.ErrNilTarget
		}

		header := http.Header{}
		header.Add("Content-Type", "application/json")
		tgt.Header = header
		tgt.Method = "POST"

		tgt.URL = "https://api-perf.saas.mynt.xyz/gcash/acquiring/order/create.htm"
		payload := `{"request":{"head":{"version":"2.0","function":"gcash.acquiring.order.create","clientId":"2018103116394800000000","clientSecret":"2018101611204819271136Oaea","reqTime":"{{currDate}}","reqMsgId":"{{guid}}"},"body":{"merchantId":"217020000001896283595","order":{"merchantTransId":"{{guid}}","merchantTransType":"CASHIER_ORDER","orderMemo":"Product SKU001","orderTitle":"QA TEST","orderAmount":{"currency":"PHP","value":"1000"},"createdTime":"{{currDate}}","expiryTime":"{{expiryDate}}"},"productCode":"51051000101000100001","subMerchantId":"217050000600748163998","subMerchantName":"","mcc":"","notificationUrls":[{"type":"PAY_RETURN","url":"https://www.yahoo.com/"},{"type":"CANCEL_RETURN","url":"https://www.google.com"},{"type":"NOTIFICATION","url":"https://www.freddyfazbear.com/order_not_available"}],"envInfo":{"orderTerminalType":"WEB","terminalType":"WEB"}}},"signature":"dYqAbeaMfJPW5qQF2tKvWuN+Zz3yJbqjVawFGfjFYtaKU3armBPCSc63hC6mM5hbdU+9XyQANUc9HuCQSAzV7YUpE/b3QmOzC5EC56pmwL7JcA41sliwpGOQacYz9QI++r3LOS0CHexcLCXM6M5KRpeexfRhgnMZSfu4JMnkfg4+oouLJzzGeWlYjSpEQizf3c8quacS3ImXbHiGhn2jPX2y04f0nr85H/6SrZXdi398EO1tausaux11xmkjdAUDTtP8iMjoTuPrv3iYk+lmCZJOXUYy3wTd3euprM+94BbTuNJABr1iE4ePFWRY1ukcIY4fhX2bxW8oMljJBSiF8w=="}`
		pp := ""
		pp = strings.Replace(payload, "{{guid}}", uuid.NewString(), -1)
		timeNow := time.Now()
		transactionExpiry := timeNow.Add(time.Hour * 24 * 14)
		pp = strings.Replace(pp, "{{currDate}}", timeNow.Format(time.RFC3339), -1)
		pp = strings.Replace(pp, "{{expiryDate}}", transactionExpiry.Format(time.RFC3339), -1)
		tgt.Body = []byte(pp)
		return nil
	}
}