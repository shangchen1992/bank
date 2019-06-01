/**
 * 
 */
package cn.soft.interview.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franklv
 * @Desc 主要是叫号机器生成排队序号以及取号
 * @Date 2019年4月15日 下午1:54:20
 */
public class NumberManager {
	private Integer lastNum = 1;
	//由于会有排队的客户，所以对象声明为队列
	private List<Integer> queue = new ArrayList<>();
	//使用同步锁限制
	public synchronized Integer generateNum(){
		queue.add(lastNum);
		//在原有号码的基础上加1
		return lastNum++;
	}
	//用于窗口叫号使用
	public synchronized Integer fetchNum(){
		if(queue.size() > 0){
			return queue.remove(0);
		}
		return null;
	}
}
