package atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalController
{
  private final IATM _bank;

  public WithdrawalController(@Autowired IATM bank)
  {
    _bank = bank;
  }

  @RequestMapping("/withdraw")
  public Withdrawal withdraw(@RequestParam(value="amount", defaultValue="0") int amount)
  {
    return _bank.withdraw(amount);
  }
}
