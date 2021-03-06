package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;
import com.sojava.beehive.framework.component.wechat.bean.User;
import com.sojava.beehive.framework.component.wechat.define.Platform;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.define.WxScope;
import com.sojava.beehive.framework.component.wechat.service.WxUserService;
import com.sojava.beehive.framework.component.wechat.tqm.service.EvidenceService;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.framework.io.Writer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/TakeEvidence")
@Scope("prototype")
public class TakeEvidence extends ActionSupport {
	private static final long serialVersionUID = 5297527713190499766L;

	@Resource private WxUserService wxUserService;

	private String wxServerId;
	private String qrcode;
	private Integer itemId;
	private Integer itemNum;
	private String itemLabel;
	private String data;
	private String staffName;
	private Integer id;
	private String code;// openid
	private String state;
	private String wxid;

	private final String WX_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    private final String JPG = "image/jpeg";

    private EvidenceService evidenceService;

	@Action("TakeEvidence.VerifyQRCode")
	public void verifyQRCode() throws Exception {
		super.execute();
		boolean valid = false;
		String errmsg = "不能识别的二维码";
		try {
			valid = evidenceService.verifyQRCode(qrcode);
			errmsg = "ok";
		}
		catch(NullPointerException ex) {
			errmsg = "空的二维码信息";
		}
		catch(Exception ex) {
			errmsg = ex.getLocalizedMessage();
		}
		JSONObject rest = new JSONObject();
		rest.accumulate("success", valid);
		rest.accumulate("errmsg", errmsg);

		new Writer(getRequest(), getResponse()).output(rest);
	}

	@Action("TakeEvidence.PullPhoto")
	public void pullPhoto() throws Exception {
		super.execute();
		JSONObject rest = new JSONObject();
		rest.put("success", true);
		rest.put("errmsg", "ok");

		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			if (!evidenceService.verifyQRCode(qrcode)) throw new WarnException("不能识别的二维码");

			URL url = new URL(String.format(WX_MEDIA_URL, WeChatInfo.getTQMAccessToken().getAccessToken(), wxServerId));
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			in = http.getInputStream();
			out = new ByteArrayOutputStream();
			byte[] photo = null, buff = new byte[1024];
			int len;
			while ((len = in.read(buff, 0, buff.length)) != -1) {
				out.write(buff, 0, len);
			}
			in.close();
			photo = out.toByteArray();
			out.close();
			http.disconnect();

			CaseHistoryEvidence e = new CaseHistoryEvidence();
			String[] str = qrcode.replaceAll("^.*\\Qcn.org.jxszyyy.casehistory.evidence.\\E", "").split("\\Q-\\E");
			e.setPaperNum(Integer.parseInt(str.length == 2 ? str[1] : str[0]));
			e.setCode(qrcode);
			e.setStandardId(Integer.parseInt(str.length == 2 ? str[0] : "1"));
			e.setItemId(itemId);
			e.setItemNum(itemNum);
			e.setItemLabel(itemLabel);
			e.setPhoto(photo);
			e.setCreateTime(new Date());
			e.setOpenid(code);
			e.setStaffName(staffName);
			evidenceService.save(e);
		}
		catch(Exception ex) {
			rest.put("success", false);
			rest.put("errmsg", ex.getLocalizedMessage());
		}
		new Writer(getRequest(), getResponse()).output(rest);
	}

	@Action("TakeEvidence.Photo")
	public void photo() throws Exception {
		super.execute();
		HttpServletResponse response = getResponse();
		User user = wxUserService.checkWxUser(
				response,
				"https://wx.jxszyyy.org.cn/WeChat/TQM/TakeEvidence.Photo.shtml?" + (wxid != null ? ("wxid=" + wxid + "&") : "") + "id=" + id,
				WeChatInfo.TQM_APPID,
				WeChatInfo.TQM_SECRET,
				WxScope.snsapi_base,
				code,
				state,
				wxid,
				Platform.TQM
			);
		if (user == null || !user.getStatus().equals("已激活")) throw new ErrorException("用户未登记或未激活，不能操作");
		byte[] buff = evidenceService.getPhoto(id);
		response.setContentType(JPG);
		response.getOutputStream().write(buff);
	}

	public String getWxServerId() {
		return wxServerId;
	}

	public void setWxServerId(String wxServerId) {
		this.wxServerId = wxServerId;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public EvidenceService getEvidenceService() {
		return evidenceService;
	}

	public void setEvidenceService(EvidenceService evidenceService) {
		this.evidenceService = evidenceService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public WxUserService getWxUserService() {
		return wxUserService;
	}

	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

}
