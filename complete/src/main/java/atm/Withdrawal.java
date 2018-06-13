package atm;

import java.util.ArrayList;
import java.util.List;

public class Withdrawal
{
  private final long _id;
  private List<Money> _money = new ArrayList<>();
  private String _result = "Ok!";

  Withdrawal(long id)
  {
    _id = id;
  }

  @SuppressWarnings("unused")
  public long getId()
  {
    return _id;
  }

  public List<Money> getMoney()
  {
    return _money;
  }

  public void setResult(String error)
  {
    _result = error;
  }

  @SuppressWarnings("unused")
  public String getResult()
  {
    return _result;
  }
}
