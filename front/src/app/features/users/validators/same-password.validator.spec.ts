import { SamePasswordValidator } from './same-password.validator';

describe('SamePasswordDirective', () => {
  it('should create an instance', () => {
    const directive = new SamePasswordValidator();
    expect(directive).toBeTruthy();
  });
});
