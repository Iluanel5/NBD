/**
 * Zadanie 2: Klasa konta bankowego
 */
class BankAccount {

  /**
   * Primary Konstruktor ustawiający początkowy stan konta na zero (nie wiem jak inaczej to można zrobić).
   */
  private var _accountBalance: Int = 0

  /**
   * Konstruktor przyjmujący początkowy stan konta
   * @param accountBalance
   */
  def this(accountBalance: Int) {
    this()
    this._accountBalance = accountBalance
  }

  /**
   * "getter" dla stanu konta
   * @return
   */
  def accountBalance = _accountBalance

  /**
   * wpłata na konto
   * @param amount
   */
  def paymentIntoAccount(amount: Int) {
    if(amount > 0)
      _accountBalance += amount
  }

  /**
   * Wypłata z konta
   * @param amount
   */
  def withdrawMoneyFromAccount(amount: Int) = {
    if (amount >= 0 && amount <= accountBalance)
      _accountBalance -= amount
  }
}
