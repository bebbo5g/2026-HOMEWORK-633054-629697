
public class Giocatore {
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore(int cfu, Borsa borsa)
	{
		this.cfu = cfu;
		this.borsa = borsa;
	}
	
	public void setCfu(int cfu)
	{
		this.cfu = cfu;
	}
	
	public int getCfu()
	{
		return this.cfu;
	}
	
	public void setBorsa(Borsa borsa)
	{
		this.borsa = borsa;
	}
	
	public Borsa getBorsa()
	{
		return this.borsa;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo)
	{
		if(borsa.addAttrezzo(attrezzo))
		{
			return true;
		}
		else
			return false;
	}
}
