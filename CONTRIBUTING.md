# Contributing to Bank Management System

Thank you for your interest in contributing to this project! This document provides guidelines for contributing.

## Getting Started

1. **Fork the repository**
2. **Clone your fork**
   ```bash
   git clone https://github.com/yourusername/bank-management-system.git
   ```
3. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## Development Setup

### Prerequisites
- Java JDK 8 or higher
- Git

### Installation
```bash
cd "Bank Management System"
javac -d bin src\*.java
```

### Running Tests
```bash
compile.bat
run-tests.bat
```

All tests must pass before submitting a pull request.

## Coding Standards

### Java Style Guide
- Use 4 spaces for indentation
- Use camelCase for variable and method names
- Use PascalCase for class names
- Add JavaDoc comments for all public methods
- Keep methods focused and under 50 lines when possible

### Code Quality
- Write unit tests for new features
- Maintain test coverage above 80%
- No compilation warnings
- Follow OOP principles (encapsulation, inheritance, polymorphism, abstraction)

### Commit Messages
- Use present tense ("Add feature" not "Added feature")
- First line should be 50 characters or less
- Reference issue numbers when applicable

Example:
```
Add overdraft limit validation

- Implement overdraft limit checking in CurrentAccount
- Add unit tests for overdraft scenarios
- Update documentation

Fixes #123
```

## Pull Request Process

1. **Update documentation** for any changed functionality
2. **Add tests** for new features
3. **Run all tests** to ensure nothing is broken
4. **Update README.md** if adding new features
5. **Create pull request** with clear description

### PR Checklist
- [ ] Code compiles without errors
- [ ] All tests pass (68/68)
- [ ] No warnings during compilation or test execution
- [ ] JavaDoc comments added for public methods
- [ ] README.md updated if necessary
- [ ] TESTING.md updated if test changes made

## Testing Guidelines

- Write tests before implementing features (TDD)
- Test both success and failure scenarios
- Use descriptive test method names
- One assertion per test when possible
- Use @BeforeEach for common setup

## Reporting Bugs

### Bug Report Template
```
**Description:**
Clear description of the bug

**Steps to Reproduce:**
1. Step one
2. Step two
3. ...

**Expected Behavior:**
What should happen

**Actual Behavior:**
What actually happens

**Environment:**
- Java Version: 
- OS: 
```

## Feature Requests

We welcome feature requests! Please include:
- Clear description of the feature
- Use case / motivation
- Proposed implementation (optional)

## Code Review Process

- All submissions require review
- Reviewers may request changes
- Once approved, changes will be merged
- Maintain respectful and constructive communication

## Questions?

Feel free to open an issue for any questions about contributing.

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing! 🚀
