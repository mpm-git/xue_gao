package per.cz.socket;



public interface IReceiver {


	void handle(byte[] bs, Transfer transfer);
}
