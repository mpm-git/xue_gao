package per.cz.event1_0;

public class MyTest 
{
	public static void main(String[] args)
	{
		MyTest test = new MyTest();
		DispatchEvent.addEventListener("com",new IMethod()
		{
			@Override
			public void excute(DEvent event)
			{
				//System.out.println("com");
			}
		});
		DispatchEvent.addEventListener("com2", new IMethod()
		{
			
			@Override
			public void excute(DEvent event)
			{
				DispatchEvent.dispatchEvent(new DEvent("com",":hello"));
				//System.out.println("com2"+event.getTarget().toString());
			}
		});
		DispatchEvent.dispatchEvent(new DEvent("com2",test));
	}

}
