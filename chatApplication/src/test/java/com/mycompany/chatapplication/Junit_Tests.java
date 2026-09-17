package com.mycompany.chatapplication;

import org.junit.Test;
import static org.junit.Assert.*;

public class Junit_Tests 
{

    // USERNAME TEST
    @Test
    public void testCheckUsername_valid() 
    {
        assertTrue(ChatApplication.checkUsername("Kyle_"));
    }

    @Test
    public void testCheckUsername_invalid() 
    {
        assertFalse(ChatApplication.checkUsername("Kyle222"));
    }

    // PASSWORD TEST
    @Test
    public void testCheckPasswordComplexity_valid() 
    {
        assertTrue(ChatApplication.checkPasswordComplexity("Che&&sec@ke99!"));
    }


    // CELL PHONE NUMBER TEST
    @Test
    public void testCheckCellNumber_valid() 
    {
        assertTrue(ChatApplication.checkCellNumber("+27821234567"));
    }

    @Test
    public void testCheckCellNumber_invalid() 
    {
        assertFalse(ChatApplication.checkCellNumber("0821234567"));
    }

    // LOGIN TEST
    @Test
    public void testLoginUser_success() 
    {
        // Set stored static values first
        ChatApplication.accountUser = "user_";
        ChatApplication.accountPass = "Pass123@";

        assertTrue(ChatApplication.loginUser("user_", "Pass123@"));
    }

    @Test
    public void testLoginUser_failure() 
    {
        ChatApplication.accountUser = "user_";
        ChatApplication.accountPass = "Pass123@";

        assertFalse(ChatApplication.loginUser("wrong_", "Pass123@"));
    }

}