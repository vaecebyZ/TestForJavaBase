package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.Comments;
import com.sybinal.shop.model.CommentsExample;
import com.sybinal.shop.model.CommentsRelation;

public interface CommentsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	long countByExample(CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int deleteByExample(CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int insert(Comments record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int insertSelective(Comments record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	List<Comments> selectByExampleWithBLOBs(CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	List<Comments> selectByExample(CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	Comments selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") Comments record, @Param("example") CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int updateByPrimaryKeySelective(Comments record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(Comments record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comments
	 * @mbg.generated  Thu Oct 27 13:19:22 CST 2016
	 */
	int updateByPrimaryKey(Comments record);

	List<CommentsRelation> selectCommentsRelationByExample(CommentsExample example);
	
	
	List<Comments> selectCommentsByCondition(Comments record);
	
	int selectCountByCondition(Comments record);
}