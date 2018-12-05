package com.niit.testCase;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.Date_Time;
import com.niit.dao.ForumReplyDAO;
import com.niit.model.ForumReply;

public class TestForumReply 
{
	private static final Logger log = LoggerFactory.getLogger(TestForum.class);

	@Autowired
	ForumReplyDAO forumReplyDAO;
	
	@Autowired
	ForumReply forumReply;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public TestForumReply()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		forumReply = (ForumReply) context.getBean("forumReply");
		forumReplyDAO = (ForumReplyDAO) context.getBean("forumReplyDAO");
	}
    @Test
	public void forumAddReply()
	{
		forumReply.setForum_id(132);
		forumReply.setRating(5);
		forumReply.setReply("Royal Enfield Himalayan");
		forumReply.setUsername("chinmay");
		Date_Time dt = new Date_Time();
		forumReply.setPostedAt(dt.getDateTime());
		
		boolean value = forumReplyDAO.addReply(forumReply);
		
		log.info("Reply added to forum "+forumReply.getForum_id()+" "+value);
	}
	
	public void deleteReply() throws NullPointerException
	{
		ForumReply reply = forumReplyDAO.getReply(142);
		log.info("Recieved "+reply.getReply());
		boolean value = forumReplyDAO.deleteReply(reply);
		log.info("Comment Deleted "+value);
	}
	@Test
	public void GetReplies()
	{
		List<ForumReply> list = forumReplyDAO.getForumReply(132);
		if(list.isEmpty() || list == null)
		{
			System.out.println("Empty");
		}
		else
		{
			for(ForumReply reply : list)
				System.out.println("ID "+reply.getReply_id()+" Reply -"+reply.getReply());
		}
	}
	
	public static void main(String[] args) 
	{
		TestForumReply tc = new TestForumReply();
		tc.forumAddReply();
//		tc.deleteReply();
		tc.GetReplies();
	}
}