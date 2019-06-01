package cn.soft.interview.bank;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;

/**
 * @author franklv
 * @Desc 银行窗口业务实现
 * @Date 
 */
public class ServiceWindow {
	private CustomerType type = CustomerType.COMMON;
	private Integer windowId = 1;
	public void setType(CustomerType type) {
		this.type = type;
	}
	public void setWindowId(Integer windowId) {
		this.windowId = windowId;
	}
	public void start(){
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			//使用匿名内部类处理
			public void run() {
				//这里需要一直取任务，使用while(true)处理
				while(true){
					//根据不同的客户类型取号
					//switch的效率比if,else要高
					String windowName = null;
					switch(type){
					case COMMON:
						commonService();
						break;
					case FAST:
						fastService();
						break;
					case VIP:
						vipService();
						break;
					}
				}
				
			}

			/**
			 * @author franklv
			 * @Desc 普通窗口为普通客户提供服务
			 * @Date 2019年4月15日 下午6:12:15
			 */
			private void commonService() {
				String windowName;
				windowName = "第"+windowId+"号"+type+"窗口";
				System.out.println(windowName+"开始取普通业务");
				Integer num = NumberMachine.getInstance().getCommonManager().fetchNum();
				if(num != null){
					//普通业务也会被快速和vip窗口调用，服务的名称要写死
					System.out.println(windowName+"为"+num+"号普通客户提供服务");
					long beginTime = System.currentTimeMillis();
					int maxRand = ProceedingTime.maxTime - ProceedingTime.minTime;
					long serveTime = new Random().nextInt(maxRand)+1+ProceedingTime.minTime;
					try {
						//模拟服务用时
						Thread.sleep(serveTime);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					long costTime = System.currentTimeMillis()-beginTime;
					System.out.println(windowName+"为"+num+"号普通客户服务完毕，用时:"+costTime/1000+"s");
				}else{
					System.out.println(windowName+"没获取到任务，先休息1s");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			private void fastService() {
				String windowName;
				windowName = "第"+windowId+"号"+type+"窗口";
				System.out.println(windowName+"开始取快速业务");
				Integer num = NumberMachine.getInstance().getFastManager().fetchNum();
				if(num != null){
					System.out.println(windowName+"为"+num+"号"+type+"客户提供服务");
					long beginTime = System.currentTimeMillis();
					//int maxRand = ProceedingTime.maxTime - ProceedingTime.minTime;
					long serveTime = ProceedingTime.minTime;
					try {
						//模拟服务用时
						Thread.sleep(serveTime);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					long costTime = System.currentTimeMillis()-beginTime;
					System.out.println(windowName+"为"+num+"号"+type+"客户服务完毕，用时:"+costTime/1000+"s");
				}
				else{
					System.out.println(windowName+"没有取到快速业务，开始取普通业务");
					//如果没取到快速业务的客户，去帮忙普通业务客户 
					commonService();
				}
			}
			private void vipService() {
				String windowName;
				windowName = "第"+windowId+"号"+type+"窗口";
				System.out.println(windowName+"开始取VIP业务");
				Integer num = NumberMachine.getInstance().getVipManager().fetchNum();
				if(num != null){
					System.out.println(windowName+"为"+num+"号"+type+"客户提供服务");
					long beginTime = System.currentTimeMillis();
					int maxRand = ProceedingTime.maxTime - ProceedingTime.minTime;
					long serveTime = new Random().nextInt(maxRand)+1+ProceedingTime.minTime;
					try {
						//模拟服务用时
						Thread.sleep(serveTime);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					long costTime = System.currentTimeMillis()-beginTime;
					System.out.println(windowName+"为"+num+"号"+type+"客户服务完毕，用时:"+costTime/1000+"s");
				}
				else{
					System.out.println(windowName+"没有取到vip业务，开始取普通业务");
					//如果没取到快速业务的客户，去帮忙普通业务客户 
					commonService();
				}
			}
		});
	}
	
}
