package character;

public class Archer extends Character {
	private boolean focus;
	public Archer() {
		super(200,1200);
		this.focus=false;
		setImage("resources/archer.jpg");
		setSkillNames("Focusshot", "Critical", "Knockback");
	}
	

	public void focusShot()
	{
		if(isFocus())
		{
			System.out.println("Already Focus!!!");
		}
		else
		{
			setFocus(true);
			System.out.println("Focus!!!");
		}
	}
	
	public boolean isFocus() {
		return focus;
	}


	public void setFocus(boolean focus) {
		this.focus = focus;
	}


	public boolean critical() //passive critical
	{
		int range=10;
		if(isFocus())
		{
			range=4;
		}
		int random = (int )(Math.random() * range + 1);
		return random <= 3? true : false ;
	}
	
	public void Knockback (Character enemy)
	{
		enemy.setStun(1);
		System.out.println("the enemy is stunned");
		int random = (int )(Math.random() * 10 + 1);
		if(random <= 2)
		{
			if(!isFocus())
			{
				setFocus(true);
				System.out.println("Bonus : the enemy is stunned,you have a time to focus");
			}
		}
		
	}
	
	@Override
	public void attack(Character enemy) {
		// TODO Auto-generated method stub
		if(critical())
		{
			enemy.attackByEnemy(this.getAtk()*2);
		}
		else
		{
			enemy.attackByEnemy(this.getAtk());
		}
		setFocus(false);
	}
	
	
	@Override
	public String getInstance() {
		return "Archer";
	}
	
	
}
