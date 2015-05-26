package per.cz.timer;

import java.util.TimerTask;

public class MyTask extends TimerTask {

	private IMethod method;
	private long period;

	public void setMethod(IMethod method) {
		this.method = method;
	}

	@Override
	public void run() {
		this.method.excute(this.period);
	}

	public void setPeriod(long period) {
		this.period=period;
	}

}
