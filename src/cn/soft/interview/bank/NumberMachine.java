
package cn.soft.interview.bank;

public class NumberMachine {
	//一个叫号机可取多种客户的号
	private NumberManager commonManager = new NumberManager();
	private NumberManager fastManager = new NumberManager();
	private NumberManager vipManager = new NumberManager();
	//外界只需要调用其manager来实现功能，get方法已足够
	public NumberManager getCommonManager() {
		return commonManager;
	}
	public NumberManager getFastManager() {
		return fastManager;
	}
	public NumberManager getVipManager() {
		return vipManager;
	}
	//单例模式
	private NumberMachine(){}
	private static NumberMachine instance = new NumberMachine();
	public static NumberMachine getInstance(){
		return instance;
	}
}
