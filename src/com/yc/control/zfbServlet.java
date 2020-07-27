package com.yc.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 import com.alipay.api.response.AlipayTradeQueryResponse;
 import com.alipay.demo.trade.config.Configs;
 import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
 import com.alipay.demo.trade.service.AlipayTradeService;
 import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;

@WebServlet("/zfb.action")
public class zfbServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Log log = LogFactory.getLog("trade_query");

		if(request.getParameter("outTradeNo")!=null){
			// 一定要在创建AlipayTradeService之前设置参数
			Configs.init("zfbinfo.properties");

			AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

			// (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
			String outTradeNo = request.getParameter("outTradeNo");
	        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
	        		.setOutTradeNo(outTradeNo);
			AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
			switch (result.getTradeStatus()) {
				case SUCCESS:
					log.info("查询返回该订单支付成功: )");

					AlipayTradeQueryResponse resp = result.getResponse();
					toPrintJson(response, 1);

					log.info(resp.getTradeStatus());
					log.info(resp.getFundBillList());
					break;

				case FAILED:
					log.error("查询返回该订单支付失败!!!");
					break;

				case UNKNOWN:
					log.error("系统异常，订单支付状态未知!!!");
					break;

				default:
					log.error("不支持的交易状态，交易返回异常!!!");
					break;
			}
	       
			
		}
	}

}
