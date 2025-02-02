const amounts = ['100', '10', '5']
const getTexts = (els) => Cypress._.map(els, 'innerText')

describe('deposit spec', () => {
  it('passes', () => {
    let transactionSum = 0
    let sumDeposit = 0;

    cy.visit('https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login')
    cy.get('.btn.btn-primary.btn-lg').contains('Customer Login').click()
    cy.get('select[name=userSelect]').select('Harry Potter')
    cy.contains('Login').click()
    cy.contains('Deposit').click()

    for (let amount of amounts) {
      cy.get("input[placeholder=\"amount\"]").type(amount);
      cy.get('.btn.btn-default').click()
      cy.get('.error.ng-binding').contains('Deposit Successful').should('be.visible')
      sumDeposit += +amount;

      cy.wait(500)
    }

    cy.get('.ng-binding').contains(sumDeposit).should('be.visible')

    cy.wait(1000)

    cy.contains('Transactions').click()
    cy.get('tr td:nth-child(2)').then(getTexts).should(vals => {
      const columnTexts = `${vals.filter(v => Number.isInteger(+v as number))}`.split(',');

      for (let text of columnTexts) transactionSum += +text
      expect(transactionSum).to.eq(sumDeposit)
    })
  })
})