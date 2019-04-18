package edu.pwap.pp.models;

import java.util.List;

public class User
{
    private long id;
    private String username;
    private String password;
    private List<Authority> authorities;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setAuthorities(List<Authority> authorities)
    {
        this.authorities = authorities;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}
