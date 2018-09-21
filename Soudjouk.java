
public class Soudjouk implements InterfacePizza{
 int price=3;
 String explanation = "Soudjuk ";
 	Soudjouk(){
 		explanation();
 	}
 	Soudjouk(InterfacePizza dekor) {
 		price += dekor.cost();
 		explanation+=dekor.explanation();
 	}
	public int cost() {
		return price;
	}
	public String explanation() {
		return explanation;
	}

}
