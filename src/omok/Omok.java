package omok;

public class Omok {
	protected boolean end;
	protected int[][] board; //0�̸� �ȵа� -1�̸� �浹, 1�̸� ��
	protected int ver,hor;
	protected int turn;
	
	Omok(int ver, int hor){
		init(ver,hor);
	}
	
	public void init(int ver, int hor){
		end = false;
		turn = 1;
		board = new int[ver+2][hor+2];
		for(int i = 1; i <= ver; i++){
			for(int j = 1; j<= hor; j++){
				board[i][j] = 0;
			}
		}
	    for (int i = 0; i < ver; i++){
	    	board[0][i] = 4;
	    	board[ver + 1][i] = 4;
	    }
	    for (int i = 0; i <hor; i++){
	    	board[i][0] = 3;
	    	board[i][hor + 1] = 3;
	    }
	}
	
	public boolean put(int x, int y){
		if(board[x][y] != 0){
			return false;
		}
		board[x][y] = turn;
		turn *= -1;
		if(WOL(x,y)){
			end = true;
		}
		return true;
	}

	
	
	boolean WOL(int x, int y){//������ �������� Ȯ���ϴ� �Լ�
	    int[] ex = new int[2];
	    int[] ey = new int[2];
	    int i, a, b, k;

	    for (i = 0; i< 2; i++){
	        ex[i] = x;
	        ey[i] = y;
	    }
	    k = 1;

	    for (b = -1; b < 2; b++){   //ey���� ��ȭ�� ����
	        for (a = 0; a < 2; a++){ //ex,ey�� [0]�� [1] üũ([0]��[1]�� k�� �̿��Ͽ�  ���� �ݴ�  �������� üũ�մϴ�.)
	            for (i = 1; i < 5; i++){ //�÷��̾ ���ڸ��κ��� �ѹ��⿡ �ִ� 4������ üũ
	                if (board[ex[a] + (-1)*k][ey[a] + b*k] == turn*(-1)){//���� �����ų� ��뺴�� ���� ������ break
	                    ex[a] = ex[a] + (-1)*k;
	                    ey[a] = ey[a] + b*k;
	                }
	                else{   break;  }
	            } k = k*(-1);   //�ݴ� ������ üũ�ϱ� ���� -1�� ����
	        }
	        if (ex[1] - ex[0] + 1 >= 5){//5�� �̻��̸� �¸�
	            return true;
	        }
	        for (i = 0; i< 2; i++){
		        ex[i] = x;
		        ey[i] = y;
		    }
		    k = 1;
	    }

	    for (i = 0; i< 2; i++){
	        ex[i] = x;
	        ey[i] = y;
	    }
	    k = 1;

	    k = -1;
	    for (a = 0; a < 2; a++){    //x�� ��ȭ���� 0�̰� y�� ��ȭ���� ���� ���, �Ʒ��� �ڵ�� ���� ����
	        for (i = 1; i < 5; i++){
	            if (board[ex[a]][ey[a] + k] == turn*(-1)){
	                ex[a] = ex[a];
	                ey[a] = ey[a] + k;
	            }
	            else{ break; }
	        }
	        k = k*-1;
	    }
	    if (ey[1] - ey[0] + 1 >= 5){
	        return true;
	    }

	    return false;
	}
	public int whoIsTurnIsIt(){
		return turn;
	}
	public boolean isItend(){
		return end;
	}
}
