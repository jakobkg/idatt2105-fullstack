// https://docs.cypress.io/api/introduction/api.html

describe("Pass known good info to the form", () => {
  it("Enters info and sends it", () => {
    cy.visit("/feedback");
    cy.get('input[id="name"]').type("Navn Navnesen");
    cy.get("button").should("be.disabled");

    cy.get('input[id="email"]').type("navn@mail.internett");
    cy.get("button").should("be.disabled");

    cy.get('textarea[id="feedback"]').type("Dette er en tilbakemelding");
    cy.get("button").should("not.be.disabled").click();
    cy.get('p').should("contain.text", "Takk!");
  });
});

describe("Pass known bad data to the form", () => {
  it("Enters an invalid email address", () => {
    cy.visit("/feedback");
    cy.get('input[id="name"]').type("Navn Navnesen");
    cy.get("button").should("be.disabled");

    cy.get('input[id="email"]').type("ikkeenmailadresse");
    cy.get("button").should("be.disabled");

    cy.get('textarea[id="feedback"]').type("Dette er en tilbakemelding");
    cy.get("button").should("be.disabled");

    cy.get('input[id="email"]').should("have.css", "border", "2px solid rgb(255, 0, 0)");
    cy.get("button").should("be.disabled");
  });
});
