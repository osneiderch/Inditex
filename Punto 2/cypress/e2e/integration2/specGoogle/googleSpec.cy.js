describe('Búsqueda en Google y validación en Wikipedia', () => {
  beforeEach(() => {
    // Ignorar errores no controlados para evitar que Cypress falle
    Cypress.on('uncaught:exception', (err) => {
      if (err.message.includes('Unexpected token') || err.message.includes('solveSimpleChallenge')) {
        return false;
      }
      return true;
    });
  });

  it.only('Busca en Google y valida información en Wikipedia', () => {
    // 1. Visitar Google
    cy.visit('https://www.google.com');

    // 2. Manejar la ventana emergente de términos y condiciones
    cy.get('body').then(($body) => {
      if ($body.find('#L2AGLb').length > 0) {
        cy.get('#L2AGLb').click(); // Hacer clic en "Aceptar todo"
      }
    });

    // 3. Ingresar la palabra clave "automatización" en la barra de búsqueda y presionar Enter
    cy.get('textarea[name="q"]').type('automatización{enter}');

    // 4. Simular la resolución del CAPTCHA (si ocurre)
    cy.wait(200000); // Tiempo de espera para verificar si aparece un CAPTCHA
    cy.get('body').then(($body) => {
      if ($body.text().includes('solveSimpleChallenge')) {
        // Simula una interacción con el CAPTCHA
        cy.log('Simulación: Resolvió el CAPTCHA manualmente.');
        return;
      }
    });

    // 5. Buscar el enlace de Wikipedia en los resultados y hacer clic
    cy.contains('a', 'Wikipedia').should('be.visible').click();

    // 6. Validar que aparece el año del primer proceso automático en Wikipedia
    cy.origin('https://es.wikipedia.org', () => {
      cy.contains(
        '1785, convirtiéndose en el primer proceso industrial completamente automatizado.'
      )
        .scrollIntoView()
        .should('be.visible')
        .screenshot(); // Tomar una captura de pantalla de la página
    });
  });
});
