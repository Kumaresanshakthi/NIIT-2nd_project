package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface CommentDAO
{
	
	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteComment(int id);
	
	public List<BlogComment> getBlogComments(String blog_id);
}