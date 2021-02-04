package com.sybinal.shop.controller.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class ApiFileUploadController extends AbstractApiController{

	@RequestMapping(value = "/user/fileUploadfile", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> newUploadFile(@RequestParam("file") List<MultipartFile> file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String path = request.getSession().getServletContext().getRealPath("/");
		Map<String, String> map = new HashMap<String, String>();
		path = path + "resources/upload/";
		FileOutputStream output = null;
		try {
			if (file != null && file.size() > 0) {
				for (MultipartFile fileObj : file) {
					String fileType = fileObj.getOriginalFilename().split("\\.")[1];			
					output = new FileOutputStream(path + uuid + "." + fileType);
					String url = "/resources/upload/";
					IOUtils.copy(fileObj.getInputStream(), output);
					map.put("attachmentPath", url + uuid + "." + fileType);
					map.put("attachmentName", uuid + "." + fileType);
					System.out.println(fileObj);
				}
			}
		} catch (Exception e) {
			map.put("errorCode", "-1");
			map.put("errorMsg", "error");
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return map;
	}
}
