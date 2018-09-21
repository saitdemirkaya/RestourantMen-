
public class HotPepper implements InterfacePizza{
	int price=1;
	 String explanation = "Pepper ";
	 HotPepper(){
	 		explanation();
	 	}
	public HotPepper(InterfacePizza dekor) {
		price+=dekor.cost();
		explanation+=dekor.explanation();
	}
	public int cost() {
		return price;
	}
	public String explanation() {
		return explanation;
	}

}
