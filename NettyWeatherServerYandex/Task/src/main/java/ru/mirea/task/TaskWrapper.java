/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mirea.task;

import io.netty.channel.ChannelHandlerContext;

public class TaskWrapper
{
	public Task task;
	public ChannelHandlerContext ctx;
	
	public TaskWrapper()
	{
		this.task=null;
		this.ctx=null;
	}
	public TaskWrapper(Task task, ChannelHandlerContext ctx)
	{
		this.task=task;
		this.ctx=ctx;
	}
}
