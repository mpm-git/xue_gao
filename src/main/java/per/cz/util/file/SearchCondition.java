package per.cz.util.file;

import java.io.File;

public abstract class SearchCondition<T>  implements ISearchCondition {
	public abstract boolean hasCondition(File file);
	public SearchCondition(T Condition)
	{
		this.condition=Condition;
	}
	public SearchCondition()
	{
		super();
	}
	private T condition;
	public void setCondition(T condition) {
		this.condition = condition;
	}
	public T getCondition() {
		return condition;
	}
}
