package mynt.gw;

import com.alipay.fc.common.util.assertion.AssertUtil;
import com.alipay.fc.fluxnet.plugin.returncode.ReturnCodeService;
import com.alipay.fc.fluxnet.plugin.returncode.model.ReturnCodeParseRequest;
import com.alipay.fc.fluxnet.plugin.returncode.model.ReturnCodeParseResult;
import com.alipay.fc.supergw.core.service.extension.common.GroovyDependencyServicePackage;
import com.alipay.supergwlite.components.common.enums.MessageFormat;
import com.alipay.supergwlite.components.common.message.CompositeData;
import com.alipay.supergwlite.components.common.message.Field;
import com.alipay.supergwlite.components.common.model.common.MessageEnvelope;
import com.alipay.supergwlite.components.unimsg.UnifyCommonMessage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AssertUtil.class, GroovyDependencyServicePackage.class})
public class GCreditAvailmentPendingDisbursementParserJUnitTest extends Assert {

    @Mock
    private ReturnCodeService returnCodeService;

    @InjectMocks
    AlipayplusIntegrationCreditAvailmentPendingDisbursementParser parser;

    private Map<String, String> extraContent;
    private Object content;
    private MessageFormat format;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(GroovyDependencyServicePackage.class);

        format = MessageFormat.TEXT;
        extraContent = new HashMap<>();
    }

    @Test
    public void test_success() {
        extraContent.put("_httpStatusCode", "200");
        content = "{\n" +
                "  \"result\": true\n" +
                "}";

        MessageEnvelope msg = new MessageEnvelope(format, content, extraContent);
        UnifyCommonMessage response = (UnifyCommonMessage) parser.parse(msg);

        CompositeData resultInfo = (CompositeData) response.getCommonBody().getData().get("resultInfo");
        Field result = (Field) response.getCommonBody().getData().get("result");
        Field resultCodeId = (Field) resultInfo.get("resultCodeId");
        Field resultStatus = (Field) resultInfo.get("resultStatus");
        Field resultMsg = (Field) resultInfo.get("resultMsg");

        assertEquals("000", resultCodeId.getValue());
        assertEquals("S07", resultStatus.getValue());
        assertEquals("Success", resultMsg.getValue());
        assertEquals("true", result.getValue());
    }

    @Test
    public void test_unknown_500() {
        extraContent.put("_httpStatusCode", "500");
        content = StringUtils.EMPTY;

        MessageEnvelope msg = new MessageEnvelope(format, content, extraContent);
        UnifyCommonMessage response = (UnifyCommonMessage) parser.parse(msg);

        CompositeData resultInfo = (CompositeData) response.getCommonBody().getData().get("resultInfo");
        Field resultCodeId = (Field) resultInfo.get("resultCodeId");
        Field resultStatus = (Field) resultInfo.get("resultStatus");
        Field resultMsg = (Field) resultInfo.get("resultMsg");

        assertEquals("999", resultCodeId.getValue());
        assertEquals("S09", resultStatus.getValue());
        assertEquals("unexpected http status code: 500", resultMsg.getValue());
    }

    @Test
    public void test_failed_general_error() {
        String httpStatusCode = "422";
        ReturnCodeParseRequest request = new ReturnCodeParseRequest();

        request.setTntInstId("MYNTW3PH");
        request.setInterfaceId("alipayplus.integration.credit.availment.pendingdisbursement");
        request.setExternalSystemId("GCREDIT");
        request.setExternalReturnCode(httpStatusCode);
        request.setExternalReturnCodeId(httpStatusCode);

        //mock ReturnCodeParseResult
        ReturnCodeParseResult parseResult = new ReturnCodeParseResult();
        parseResult.setUnimsgBizStatusCode("S08");
        parseResult.setUnimsgReturnCode("083");

        extraContent.put("_httpStatusCode", httpStatusCode);
        content = "{\n" +
                "  \"message\": \"General Error\"\n" +
                "}\n";

        MessageEnvelope msg = new MessageEnvelope(format, content, extraContent);
        PowerMockito.when(GroovyDependencyServicePackage.g(ReturnCodeService.class)).thenReturn(returnCodeService);
        Mockito.when(returnCodeService.parse(Mockito.any())).thenReturn(parseResult);
        UnifyCommonMessage response = (UnifyCommonMessage) parser.parse(msg);

        CompositeData resultInfo = (CompositeData) response.getCommonBody().getData().get("resultInfo");
        Field resultCodeId = (Field) resultInfo.get("resultCodeId");
        Field resultStatus = (Field) resultInfo.get("resultStatus");
        Field resultMsg = (Field) resultInfo.get("resultMsg");

        assertEquals("083", resultCodeId.getValue());
        assertEquals("S08", resultStatus.getValue());
        assertEquals("General Error", resultMsg.getValue());
    }


}