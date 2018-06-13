package atm;

/**
 * User: mrmac
 * Date: 2018-06-12
 * Time: 19:39
 * Copyright - all rights reserved
 */
public class Money
{
  public enum MoneyType { NOTE, LARGE_COIN, SMALL_COIN }

  public int value;
  public int amount;
  public MoneyType type;

  Money(int v, int a, MoneyType mt)
  {
    value = v;
    amount = a;
    type = mt;
  }
}
