package atm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ATM implements IATM
{
  private final AtomicLong counter = new AtomicLong();

  private ArrayList<Money> _vault = new ArrayList<>();
  private final Object _lock = new Object();

  public ATM()
  {
    _vault.add(new Money(1000, 100, Money.MoneyType.NOTE));
    _vault.add(new Money( 500, 100, Money.MoneyType.NOTE));
    _vault.add(new Money(200, 100, Money.MoneyType.NOTE));
    _vault.add(new Money(100, 100, Money.MoneyType.NOTE));
    _vault.add(new Money(50, 100, Money.MoneyType.NOTE));
    _vault.add(new Money( 20, 100, Money.MoneyType.LARGE_COIN));
    _vault.add(new Money(10, 100, Money.MoneyType.SMALL_COIN));
    _vault.add(new Money(5, 100, Money.MoneyType.LARGE_COIN));
    _vault.add(new Money(2, 100, Money.MoneyType.LARGE_COIN));
    _vault.add(new Money(1, 100, Money.MoneyType.SMALL_COIN));

    // In order for the extraction algorithm to work, money in the vault must be sorted by value
    Collections.sort(_vault, (m1, m2) -> -Float.compare(m1.value,m2.value));
  }

  @Override
  public Withdrawal withdraw(int amount)
  {
    synchronized (_lock)
    {
      Withdrawal w = new Withdrawal(counter.incrementAndGet());
      if(extractFromVault(amount,null))
        extractFromVault(amount, w);
      else
        w.setResult("Not enough money in the ATM!");
      return w;
    }
  }

  /**
   * Extract money from the vault. Perform a dryrun if no withdrawal is given and return true if extraction is possible.
   * @param w Withdrawal object to receive money or null to perform a dryrun
   * @return True if there's sufficient money in the ATM
   */

  private boolean extractFromVault(int amount, Withdrawal w)
  {
    for(Money m : _vault)
    {
      int cnt = amount / m.value;
      if(cnt>m.amount)
        cnt = m.amount;
      if(cnt>0)
      {
        amount -= cnt*m.value;
        if(w!=null)
        {
          m.amount -= cnt; // Remove from the vault
          w.getMoney().add(new Money(m.value,cnt,m.type)); // Add to the withdrawal
        }
      }
    }
    return amount == 0;
  }
}
