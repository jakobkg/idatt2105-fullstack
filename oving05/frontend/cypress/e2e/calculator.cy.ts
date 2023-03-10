// https://docs.cypress.io/api/introduction/api.html

describe("Do maths", () => {
  it("calculates 1 + 1", () => {
    cy.visit("/");
    cy.contains("button", "1").click();
    cy.contains("button", "+").click();
    cy.contains("button", "1").click();
    cy.contains("button", "=").click();
    cy.get("input").should((verifyResult) => {
      expect(verifyResult).have.value("2");
    });
  });

  it("calculates 5 - 2", () => {
    cy.visit("/");
    cy.contains("button", "5").click();
    cy.contains("button", "-").click();
    cy.contains("button", "2").click();
    cy.contains("button", "=").click();
    cy.get("input").should((verifyResult) => {
      expect(verifyResult).have.value("3");
    });
  });

  it("calculates 3 * 3", () => {
    cy.visit("/");
    cy.contains("button", "3").click();
    cy.contains("button", "*").click();
    cy.contains("button", "3").click();
    cy.contains("button", "=").click();
    cy.get("input").should((verifyResult) => {
      expect(verifyResult).have.value("9");
    });
  });

  it("clears", () => {
    cy.visit("/");
    for (let i = 0; i < 5; i++) {
      cy.contains("button", "1").click();
    }
    cy.contains("button", "C").click();
    cy.get("input").should((verifyResult) => {
      expect(verifyResult).have.value("");
    });
  });
});
