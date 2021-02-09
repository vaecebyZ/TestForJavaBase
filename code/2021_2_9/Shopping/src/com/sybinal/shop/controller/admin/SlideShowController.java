package com.sybinal.shop.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sybinal.shop.common.PageInformation;
import com.sybinal.shop.mapper.SlideShowMapper;
import com.sybinal.shop.model.ProductCategory;
import com.sybinal.shop.model.SlideShow;
import com.sybinal.shop.utils.PagingTool;
import com.sybinal.shop.utils.UserUtils;

@Controller
public class SlideShowController {
	@Autowired
	SlideShowMapper slideShowMapper;
	@Autowired
	PagingTool pagingTool;
	
	//去幻灯片
	@RequestMapping(value = "/admin/slideshow", method = RequestMethod.GET)
	public ModelAndView getSlideshow() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/slideshow/slideShowList");
		return model;
	}
	
	//去添加
		@RequestMapping(value = "/admin/slideshow/add", method = RequestMethod.GET)
		public ModelAndView getSlideshowAdd() {
			ModelAndView model = new ModelAndView();
			model.setViewName("admin/slideshow/slideShowEdit");
			return model;
		}
	//保存
		@RequestMapping(value = "/admin/slideshow/save", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> saveSlideShow(@RequestBody SlideShow slideShow){
			System.out.println(slideShow);
			Map<String, Object> map = new HashMap<String, Object>();
			int i = 0;
			slideShow.setAdder("admin");
			if(slideShow.getId() == null){
				
				i = slideShowMapper.addSlideShow(slideShow);
			}else{
				i = slideShowMapper.updateSlideShow(slideShow);
				
			}
			if (i > 0) {
				map.put("error", "0"); // 成功
				map.put("data", slideShow); // 数据
			} else {
				map.put("error", "-1"); // 失败
				map.put("data", slideShow); // 数据成
			}
			return map;
		}
		//删除
		@RequestMapping(value = "/admin/slideshow/del", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> deleteSlideShow(@RequestBody Integer id){
			System.out.println("76");
			Map<String, Object> map = new HashMap<String, Object>();
			
				int i = slideShowMapper.delSlideShow(id);
				//int i = productCategoryService.delteProductCategory(id);
				if(i > 0){
					map.put("error", "0"); // 成功
				}else{
					map.put("error", "-1"); // 失败
				}
			
			return map;
		}
	
	//列表数据
	@RequestMapping(value = "/admin/slideShowinfoDataTable", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> slideShowinfoDataTable(PageInformation pageInfo,SlideShow slideShow){
		
		int pageCount = slideShowMapper.selectCountByCondition();
				//slideShowMapper.selectSlideShowtByCondition();
				
		List<SlideShow> listsShows = pagingTool.PagingData(
					"com.sybinal.shop.mapper.SlideShowMapper.selectSlideShowtByCondition", null,
					Integer.parseInt(pageInfo.getiDisplayStart()) / Integer.parseInt(pageInfo.getiDisplayLength()) + 1,
					Integer.parseInt(pageInfo.getiDisplayLength()));
		
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("sEcho", pageInfo.getsEcho());
		datas.put("iTotalRecords", pageCount);
		datas.put("iTotalDisplayRecords", pageCount);
		datas.put("aaData", listsShows);
		
		
		return datas;
	}
	
	//去编辑
	@RequestMapping(value = "/admin/slideshow/edit", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getSlideShowEdit(@RequestParam("id")Integer id){
		ModelAndView model = new ModelAndView();
		SlideShow slideShow = slideShowMapper.selectSlideShowById(id);
		if(slideShow != null) {
			model.addObject("slideshow",slideShow);
		}
		model.setViewName("admin/slideshow/slideShowEdit");
		return model;
	}
	
	
	
	
}
