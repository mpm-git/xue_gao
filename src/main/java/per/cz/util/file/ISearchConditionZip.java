package per.cz.util.file;

import java.util.zip.ZipEntry;

/**
 * ���������� �ӿ��� �������ڴ˻��Ͻ��м̳С�
 * @author HJL
 *
 */
public interface ISearchConditionZip {
	public boolean hasCondition(ZipEntry zip);
}
