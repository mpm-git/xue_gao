package per.cz.util.file.handler;

import java.io.File;

/**
 * ���ڶ�һϵ����ص��ļ�����������ʱ��
 * @author HJL
 *
 */
public interface IRenameHandler {

	/**
	 * 
	 * @param nowFile ��ǰ��Ҫ��������ļ�
	 * @param preFile ǰһ�����������ļ�
	 * @return ��ǰ��������õĽ��
	 */
	File execute(File nowFile,File preFile);

}
