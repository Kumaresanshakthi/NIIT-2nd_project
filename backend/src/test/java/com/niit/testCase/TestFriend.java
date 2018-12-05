package com.niit.testCase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;

public class TestFriend
{

	private static final Logger log = LoggerFactory.getLogger(TestFriend.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public TestFriend()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		friendDAO = (FriendDAO) context.getBean("friendDAO");
		friend = (Friend) context.getBean("friend");
	}
	@Test
	public void addFriend()
	{
		friend.setUserID("shakthi");
		friend.setFriendID("kumar");
		friend.setStatus('P');
		friend.setFriendFName("kumar");
		friend.setUserFName("shakthi");
		friend.setUserIsOnline('N');
		friend.setFriendisOnline('N');
		boolean value = friendDAO.get(friend.getUserID(), friend.getFriendID());
		log.info("Value - "+value);
		if(value == true)
			friendDAO.save(friend);
		else
			System.out.println("You have already sent friend request..");
	}
	@Test
	public void acceptFriend()
	{
		String userID = "sha";
		String friendID = "kumar";
		boolean value = friendDAO.accept(userID, friendID);
		log.info("Value "+value);
		System.out.println("Success");
	}
	@Test
	public void getFriendList()
	{
		String username = "chinmay";
		List<Friend> list = new ArrayList<Friend>();
		list = friendDAO.getFriendList(username);
		System.out.println(list);
		if(list.isEmpty())
		{
			System.out.println("Socialize bro.. u need some friends");
		}
		else
		{
			System.out.println(list.get(0));
			for(int i = 0; i<list.size(); i++)
			{
				System.out.println(list.get(i).getFriendFName());
				System.out.println("Friend Name - "+list.get(i).getFriendID()+list.get(i).getUserID());
		    }
		}
		
	}
	@Test
	public void removeFriend()
	{
		String userID = "XYZ";
		String friendID = "Varun";
		friendDAO.removeFriend(userID, friendID);
	}
	@Test
	public void setOnline()
	{
		boolean x = friendDAO.setUsersOnline("kumar");
		if(x)
			System.out.println("Success...");
		else
			System.out.println("Update Failed");
	}
	@Test
	public void setOffline()
	{
		boolean x = friendDAO.setUsersOffline("kumar");
		if(x)
			System.out.println("Success...");
		else
			System.out.println("Update Failed");
	}
	
	public static void main(String[] args) 
	{
		TestFriend test = new TestFriend();
	test.addFriend();
		test.acceptFriend();
		test.removeFriend();
		test.getFriendList();
		test.setOnline();
	test.setOffline();
	}
}