class GooglePage {
    visit() {
      cy.visit('https://www.google.com')
      cy.title().should('eq',"Google") 
    }
  
    typeSearch(text) {
      cy.get("[name='q']").should('be.visible').type(text).type('{enter}')
      cy.wait(3000)
    }

    clickOnLink(linkText) {
    cy.contains(linkText).click()
    }
      
    }
  
  
  export default GooglePage