package com.niit.testCase;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.Date_Time;
import com.niit.dao.CommentDAO;
import com.niit.model.BlogComment;

public class TestComment 
{
	private static final Logger log = LoggerFactory.getLogger(TestComment.class);
	
	@Autowired 
	CommentDAO commentDAO;
	
	@Autowired
	BlogComment blogComment;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public TestComment()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blogComment = (BlogComment) context.getBean("blogComment");
		commentDAO = (CommentDAO) context.getBean("commentDAO");
	}
@Test
	public void addBlogComment()
	{
		blogComment.setComment("Good information");
		blogComment.setBlog_id("Aeroplane");
		Date_Time dt = new Date_Time();
		blogComment.setPostedAt(dt.getDateTime());
		blogComment.setRating(4);
		blogComment.setUsername("testuser");
		log.info("Comment Recieved "+blogComment.getComment());
		boolean value = commentDAO.addBlogComment(blogComment);
		log.info("Added comment "+value);
	}
	
	public void deleteComment()
	{
		int id  = 122;
		boolean value = commentDAO.deleteComment(id);
		log.info("Delete comment "+value);
	}
	@Test
	public void list()
	{
		String blog = "Aeroplane";
		List<BlogComment> list = commentDAO.getBlogComments(blog);
		System.out.println(list);
		System.out.println(list.size());
		if(list == null || list.isEmpty())
		{
			System.out.println("No comments found");	
		}
		else
		{
			for(BlogComment bc : list)
			{
				System.out.println(bc.getComment());
			}
		}
	}
	
	public static void main(String[] args)
	{
		TestComment tc = new TestComment();
		tc.addBlogComment();
//		tc.deleteComment();
	tc.list();
		System.out.println("Success");
	}
}