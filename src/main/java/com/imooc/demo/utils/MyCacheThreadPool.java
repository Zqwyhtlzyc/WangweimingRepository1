package com.imooc.demo.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池工具类
 *
 */
public class MyCacheThreadPool {
	
	//默认线程池的基本大小为20
	private static final int DEFAULT_POOL_SIZE=20;
	private static final int DEFAULT_POOL_MAX_SIZE=160;
	private static final int DEFAULT_QUEUE_MAX_SIZE=100000;
	private static ExecutorService exec;
	
	private static BlockingQueue<Runnable> workQueue;
	
	private MyCacheThreadPool(){}
	
	/**
	 * 使用newCachedThreadPool创建线程池，修改线程池基本大小为默认数量
	 * @return
	 */
	public static ExecutorService newThread(){
		if(exec==null){
			synchronized (MyCacheThreadPool.class) {
				workQueue=new LinkedBlockingDeque<Runnable>(DEFAULT_QUEUE_MAX_SIZE);
				exec=new ThreadPoolExecutor(DEFAULT_POOL_SIZE,DEFAULT_POOL_MAX_SIZE,60L,TimeUnit.SECONDS,workQueue);
			}
		}
		return exec;
	}

	public static BlockingQueue<Runnable> getWorkQueue() {
		return workQueue;
	}

	public static void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		MyCacheThreadPool.workQueue = workQueue;
	}
}
