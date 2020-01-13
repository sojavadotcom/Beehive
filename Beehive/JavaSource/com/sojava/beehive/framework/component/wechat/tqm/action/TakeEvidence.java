package com.sojava.beehive.framework.component.wechat.tqm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.wechat.bean.CaseHistoryEvidence;
import com.sojava.beehive.framework.component.wechat.define.WeChatInfo;
import com.sojava.beehive.framework.component.wechat.tqm.service.EvidenceService;
import com.sojava.beehive.framework.exception.WarnException;
import com.sojava.beehive.framework.io.Writer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Namespace("/WeChat/TQM")
@Controller("WeChat/TQM/TakeEvidence")
@Scope("prototype")
public class TakeEvidence extends ActionSupport {
	private static final long serialVersionUID = 5297527713190499766L;

	private List<Map<String, Object>> list;
	private String wxServerId;
	private String qrcode;
	private Integer itemNum;
	private String itemLabel;
	private String data;
	private String wxId;
	private String wxName;
	private Integer id;

	private final String WX_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    private final String JPG = "image/jpeg;charset=UTF-8";

    private EvidenceService evidenceService;

	@Action(value = "TakeEvidence.CaseHistory", results = {
		@Result(name = "Evidence", location = "CaseHistoryEvidence.jsp", params = {"list", "%{list}"})
	})
	public String caseHistory() throws Exception {
		super.execute();
		list = new ArrayList<Map<String,Object>>();
		for (int i = 1; i <= 50; i ++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			item.put("label", "第 " + i + " 项");
			list.add(item);
		}
		return "Evidence";
	}

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
			e.setId(Integer.parseInt(qrcode.replaceAll("[\\D].", "")));
			e.setCode(qrcode);
			e.setItemNum(itemNum);
			e.setItemLabel(itemLabel);
			e.setPhoto(photo);
			e.setCreateTime(new Date());
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
		byte[] buff = evidenceService.getPhoto(id);
		getResponse().setContentType(JPG);
		OutputStream out = null;
		try {
			out = getResponse().getOutputStream();
			out.write(buff);
			out.flush();
		}
		finally {
			out.close();
		}
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
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

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
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

}
