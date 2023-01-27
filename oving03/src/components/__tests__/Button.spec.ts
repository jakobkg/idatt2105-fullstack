import { describe, expect, it } from "vitest";

import { mount } from "@vue/test-utils";
import Button from "../Button.vue";

describe("HelloWorld", () => {
  it("renders properly", () => {
    const button = mount(Button, {
      props: {
        value: "Hello Vitest",
        callback: () => {},
      },
    });
    expect(button.text()).toContain("Hello Vitest");
  });

  it("performs its callback as expected", () => {
    const button = mount(Button, {
      props: {
        value: "Hello Vitest",
        callback: () => {
          return 5;
        },
      },
    });
    expect(button.props().callback()).equal(5);
  });
});
