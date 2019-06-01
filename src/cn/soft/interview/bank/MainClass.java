/**
 * 
 */
package cn.soft.interview.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author franklv
 * @Desc 程序主体
 * @Date 2019年4月16日 上午9:41:45
 */
public class MainClass {

	/**
	 * @author franklv
	 * @Desc 生成窗口，业务展示
	 * @Date 2019年4月16日 上午9:41:45
	 */
	public static void main(String[] args) {
		
		//这里剩的任务为窗口的创建，以及号码的生成
		//首先，根据业务要求创建普通窗口，并启动
		for(int i = 0; i<4;i++){
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setWindowId(i+1);
			commonWindow.setType(CustomerType.COMMON);
			commonWindow.start();
		}
		//创建快速窗口，并启动
		ServiceWindow fastWindow = new ServiceWindow();
		fastWindow.setWindowId(1);
		fastWindow.setType(CustomerType.FAST);
		fastWindow.start();
		//创建vip窗口，并启动
		ServiceWindow vipWindow = new ServiceWindow();
		vipWindow.setType(CustomerType.VIP);
		vipWindow.setWindowId(1);
		vipWindow.start();
		
		//根据业务要求生成客户拜访（即产生不同类型的客户号）
		//生成普通客户的号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
			new Runnable() {
				public void run() {
					//号码是调用numberMachine来生成的
					Integer num = NumberMachine.getInstance().getCommonManager().generateNum();
					System.out.println("第"+num+"号普通客户等待服务");
				}
			}, 
			0,
			ProceedingTime.COMMON_VISIT_TIME,
			TimeUnit.SECONDS
		);
		//生成快速客户的号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
			new Runnable() {
				public void run() {
					//号码是调用numberMachine来生成的
					Integer num = NumberMachine.getInstance().getFastManager().generateNum();
					System.out.println("第"+num+"号快速客户等待服务");
				}
			}, 
			0,
			ProceedingTime.COMMON_VISIT_TIME*2,
			TimeUnit.SECONDS
		);
		//生成vip客户的号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
			new Runnable() {
				public void run() {
					//号码是调用numberMachine来生成的
					Integer num = NumberMachine.getInstance().getVipManager().generateNum();
					System.out.println("第"+num+"号vip客户等待服务");
				}
			}, 
			0,
			ProceedingTime.COMMON_VISIT_TIME*6,
			TimeUnit.SECONDS
		);
		
	}

}
