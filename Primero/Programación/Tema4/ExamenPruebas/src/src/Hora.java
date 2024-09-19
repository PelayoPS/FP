package src;

public class Hora {
	private int hh;
	private int mm;
	private int ss;
	
	public Hora() {
		this.hh=0;
		this.mm=0;
		this.ss=0;
	}
	
	public Hora(int h, int m, int s) {
		if(h>=0&&h<=23) {
			this.hh=h;
		}else {
			this.hh=0;
		}
		
		if(m>=0&&m<=59) {
			this.mm=m;
		}else {
			this.mm=0;
		}
		
		if(s>=0&&s<=59) {
			this.ss=s;
		}else {
			this.ss=0;
		}
		
	}
	
	
	public int getHh() {
		return hh;
	}

	public void setHh(int hh) {
		this.hh = hh;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
	}

	public boolean horasIguales(Hora h1, Hora h2) {
		if(h1.getHh()==h2.getHh()&&h1.getMm()==h2.getMm()&&h1.getSs()==h2.getSs()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void asignacion(Hora h1) {
		this.hh=h1.getHh();
		this.mm=h1.getMm();
		this.ss=h1.getSs();
		
		
	}
	
	
	public Hora suma(Hora h1, Hora h2) {
		Hora h3;
		h3=new Hora();
		int sumaSegundos;
		sumaSegundos=h1.getSs()+h2.getSs();
		if(sumaSegundos>=60) {
			h3.setSs(60-sumaSegundos);
		}else {
			h3.setSs(sumaSegundos);
		}
		//...
		
		return h3;
	}
	
	
	public boolean mayorQue(Hora h1, Hora h2) {
		if(h1.getHh()>=h2.getHh()) {
			return true;
		}else if(h2.getHh()>h1.getHh()) {
			return false;
		}else {
			return false;
		}
		
	}
	
	
	public String verHora() {
		return "HORA: "+this.hh+":"+this.mm+":"+this.ss;
	}
	
}
















