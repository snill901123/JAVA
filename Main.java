class BookMaker{
	public int treasure=50;
}
class Player extends Thread{
	Player another;
	int treasure=0;
	String name;
	BookMaker b;
	public Player(BookMaker b,String name) {
		this.b=b;
		this.name=name;
	}
	public void add(Player  another) {
		this.another=another;
	}
	public void run() {
		/*
		  sign=0 represent Paper
		  sign=1 represent Scissors
		  sign=2 represent stone
		*/  
		while(b.treasure!=0) {
			int bookMaker_sign=(int)(Math.random()*10%3);
			int player_sign=(int)(Math.random()*10%3);
			takeOrStay(i,b,bookMaker_sign,player_sign);
		}
	}
	public synchronized void takeOrStay(int round,BookMaker bm,int b_sign,int p_sign) {
		if(b_sign==0 && p_sign==1 && bm.treasure>=5) {        //b=paper,p=scissors,p win
			bm.treasure-=5;
			treasure+=5;
		}else if(b_sign==0 && p_sign==2 && treasure>=3) {   //b=paper,p=stone,b win
			bm.treasure+=3;
			treasure-=3;
		}else if(b_sign==1 && p_sign==0 && treasure>=3) {   //b=scissors,p=paper,b win
			bm.treasure+=3;
			treasure-=3;
		}else if(b_sign==1 && p_sign==2 && bm.treasure>=5) {  //b=scissors,p=stone,p win
			bm.treasure-=5;
			treasure+=5;
		}else if(b_sign==2 && p_sign==0 && bm.treasure>=5) {  //b=stone,p=paper,p win
			bm.treasure-=5;
			treasure+=5;
		}else if(b_sign==2 && p_sign==0 && treasure>=3) {   //b=stone,p=scissors,b win
			bm.treasure+=3;
			treasure-=3;
		}
		String b_strSign=signToString(b_sign);
		String p_strSign=signToString(p_sign);
		System.out.println("Round "+round+" with "+name);
		System.out.println("BookMaker : "+b_strSign+","+name+" : "+p_strSign);
		System.out.println("BookMaker has "+b.treasure+" treasure.");
		System.out.println(name+" has "+treasure+" treasure.");
		System.out.println(another.name+" has "+another.treasure+" treasure.");
	}
	public String signToString(int sign) {  //Translate sign to String
		String str=null;
		if(sign==0) {
			str = "Paper";
		}else if(sign==1) {
			str = "Scissors";
		}else {
			str = "Stone";
		}
		return str;
	}
}
public class Main {
	public static void main(String[]argv) {
		BookMaker book=new BookMaker();
		Player playerA=new Player(book,"A");
		Player playerB=new Player(book,"B");
		playerA.add(playerB);
		playerB.add(playerA);
		System.out.println("BookMaker has 50 treasure");
		System.out.println("A has "+playerA.treasure+" treasure");
		System.out.println("B has "+playerB.treasure+" treasure\n");
		playerA.start();
		playerB.start();
	}
}
