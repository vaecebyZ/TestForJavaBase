package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sybinal.shop.model.SlideShow;

public interface SlideShowMapper {
	
	@Select("select * from slideshow")
	List<SlideShow> selectSlideShowtByCondition();

	@Select("select count(*) from slideshow")
	int selectCountByCondition();

	@Select("select * from slideshow where id=#{id}")
	SlideShow selectSlideShowById(Integer id);

	@Insert("insert into slideshow (name,introduction,adder,image) values(#{name},#{introduction},#{adder},#{image})")
	int addSlideShow(SlideShow slideShow);
	
	@Update("UPDATE slideshow set `name` = #{name} ,introduction=#{introduction},adder=#{adder},image = #{image} where id  = #{id}")	
	int updateSlideShow(SlideShow slideShow);

	@Delete("delete from slideshow where id = #{id}")
	int delSlideShow(Integer id);
	
}
