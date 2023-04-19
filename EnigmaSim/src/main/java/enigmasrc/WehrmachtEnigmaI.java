package enigmasrc;

public class WehrmachtEnigmaI implements AbstractEnigma{
	private  AbstractRotor[]rotors;
	Plugboard plugboard;
	
	//UKB-A,B,Cを使用する場合のコンストラクタ。
	public WehrmachtEnigmaI(int[]rRtrSet,int[]mRtrSet,int[]lRtrSet,
			int rflctrSet,String pbSet){
		this.plugboard =new Plugboard(pbSet);
		Rotor rightRotor=new Rotor(rRtrSet[0], rRtrSet[1],rRtrSet[2]);
		Rotor middleRotor=new Rotor(mRtrSet[0], mRtrSet[1],mRtrSet[2]);
		Rotor leftRotor=new Rotor(lRtrSet[0], lRtrSet[1],lRtrSet[2]);
		Reflector reflector=new Reflector(rflctrSet);
		setConnection(reflector,leftRotor,middleRotor,rightRotor);
	}
	//UKB-Dを使用する場合のコンストラクタ。
	public WehrmachtEnigmaI(int[]rRtrSet,int[]mRtrSet,int[]lRtrSet,
			String ukwDSet,String pbSet){
		this.plugboard =new Plugboard(pbSet);
		Rotor rightRotor=new Rotor(rRtrSet[0], rRtrSet[1],rRtrSet[2]);
		Rotor middleRotor=new Rotor(mRtrSet[0], mRtrSet[1],mRtrSet[2]);
		Rotor leftRotor=new Rotor(lRtrSet[0], lRtrSet[1],lRtrSet[2]);
		Reflector reflector=new Reflector(ukwDSet);
		setConnection(reflector,leftRotor,middleRotor,rightRotor);
	}
	@Override
	public String input(String str) {
				char[]array;
				str =str.toUpperCase();
				array = str.toCharArray();
				for (int i = 0; i < array.length; i++) {
					if(CodeTables.checkIsIncompatible(array[i])) {
						continue;
					}
					CirculatingNumber n=this.plugboard.goThrough(CirculatingNumber.setNumber(CodeTables.convert(array[i])));
					array[i]=CodeTables.convert(n.getValue());
					str=new String(array);
				} 
		return str;
	}
	
	
	@Override
	public void setConnection(AbstractRotor...rotors) {
		this.rotors=rotors;
		this.plugboard.setNextRotor(rotors[3]);
		rotors[3].setNextRotor(rotors[2]);
		rotors[2].setNextRotor(rotors[1]);
		rotors[1].setNextRotor(rotors[0]);			
	}
}
