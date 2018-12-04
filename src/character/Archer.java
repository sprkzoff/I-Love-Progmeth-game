package character;

public class Archer extends Character {
	private int focus;
	public Archer() {
		super(200, 800);
		this.focus = 0;
		setImage("resources/archer.jpg");
		setSkillNames("Focus Shot", "Critical", "Knockback");
	}
	

	public boolean focusShot()
	{
		if(isFocus()) return false;
		else {
			setFocus(2);
			return true;
		}
	}
	
	public int getFocus() {
		return focus;
	}
	
	public boolean isFocus() {
		return focus > 0;
	}


	public void setFocus(int focus) {
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
	
	public boolean Knockback (Character enemy)
	{
		enemy.setStun(1);
		System.out.println("the enemy is stunned");
		int random = (int )(Math.random() * 10 + 1);
		if(random <= 2)
		{
			if(!isFocus())
			{
				setFocus(this.focus + 2);
				System.out.println("Bonus : the enemy is stunned,you have a time to focus");
				return true;
			}
		}
		return false;
	}
	
	public boolean attackWithCritical(Character enemy) {
		if(critical())
		{
			enemy.attackByEnemy(this.getAtk()*2);
			return true;
		}
		else
		{
			enemy.attackByEnemy(this.getAtk());
			return false;
		}
	}
	
	
	@Override
	public String getInstance() {
		return "Archer";
	}
	
	
}
