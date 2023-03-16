import java.util.HashMap;

public class ConvModel {
	private String coin1;
	private String coin2;
	// private String valuta;
	private float userInput;
	private float value;
	private float result;

	public ConvModel() {

	}

	public String getCoin1() {
		return coin1;
	}

	public void setCoin1(String coin1) {
		this.coin1 = coin1;
	}

	public String getCoin2() {
		return coin2;
	}

	public void setCoin2(String coin2) {
		this.coin2 = coin2;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float f) {
		this.value = f;
	}

	public float getUserInput() {
		return userInput;
	}

	public void setUserInput(float userInput) {
		this.userInput = userInput;
	}

	public void convert(String curr1, String curr2, Float value) {
		
		float result=0;
		HashMap<String, HashMap<String, Float>> currencyOut= new HashMap<String, HashMap<String, Float>>();
		HashMap<String, Float> currencyIn1= new HashMap<String, Float>();
		HashMap<String, Float> currencyIn2= new HashMap<String, Float>();
		HashMap<String, Float> currencyIn3= new HashMap<String, Float>();
		HashMap<String, Float> currencyIn4= new HashMap<String, Float>();
		//key: RON value: [HASHMAP{ key: EUR, value: 1.2}]
		//RON:	EUR  
		//		USD
		//EUR:	RON
		//		USD
		//USD:	RON
		//		EUR
		
		currencyIn1.put("EUR", (float) 0.2029);
		currencyIn1.put("USD", (float) 0.2132);
		currencyIn1.put("RON", (float) 1);
		currencyIn1.put("GBP", (float) 0.1798);
		currencyOut.put("RON", currencyIn1);
		
		currencyIn2.put("RON", (float) 4.9293);
		currencyIn2.put("USD", (float) 1.0512);
		currencyIn2.put("GBP", (float) 0.8864);
		currencyIn2.put("EUR", (float) 1);
		currencyOut.put("EUR", currencyIn2);
		
		currencyIn3.put("RON", (float) 4.6894);
		currencyIn3.put("EUR", (float) 0.9513);
		currencyIn3.put("USD", (float) 1);
		currencyIn3.put("GBP", (float) 0.8433);
		currencyOut.put("USD", currencyIn3);
		
		currencyIn4.put("RON", (float) 5.5610);
		currencyIn3.put("EUR", (float) 1.1282);
		currencyIn3.put("USD", (float) 1.1859);
		currencyIn3.put("GBP", (float) 1);
		currencyOut.put("GBP", currencyIn4);
		//System.out.println(currencyOut.get(curr1).get(curr2));
		
		this.setResult(value* currencyOut.get(curr1).get(curr2));
		
		
	}

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}

}
