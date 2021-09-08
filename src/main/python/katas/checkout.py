from enum import Enum
from unittest import TestCase
from math import floor


class Product(Enum):
    A = 50
    B = 30
    C = 20
    D = 15

    def __init__(self, unit_price):
        self.unit_price = unit_price


class SpecialPrice:
    def apply(self, shopping_cart):
        pass


class GroupSpecialPrice(SpecialPrice):
    def __init__(self, product, group_size, price):
        self.product = product
        self.group_size = group_size
        self.price = price

    def apply(self, shopping_cart):
        filtered_products = list(filter(lambda p: p == self.product, shopping_cart.products))
        apply_count = floor(len(filtered_products) / self.group_size)
        shopping_cart.price += apply_count * self.price
        for i in range(self.group_size * int(apply_count)):
            shopping_cart.to_process.remove(self.product)
        return shopping_cart


class Discount:
    def calculate(self, products):
        pass


class GroupDiscount(Discount):
    def __init__(self, product, group_size, free_units):
        self.product = product
        self.group_size = group_size
        self.free_units = free_units

    def calculate(self, products):
        apply_count = len(list(filter(lambda p: p == self.product, products))) / self.group_size
        return apply_count * self.product.unit_price * self.free_units


class MinUnitsGlobalDiscount(Discount):
    def __init__(self, product, min_units, discount_percentage):
        self.product = product
        self.min_units = min_units
        self.discount_percentage = discount_percentage

    def calculate(self, products):
        product_count = len(list(filter(lambda p: p == self.product, products)))
        if product_count >= self.min_units:
            return sum(map(lambda p: p.unit_price, products)) * self.discount_percentage


class ShoppingCart:
    def __init__(self, products):
        self.products = [Product[p] for p in products]
        self.to_process = self.products.copy()
        self.price = 0


def checkout(shopping_cart, special_prices=None, discounts=None):
    if special_prices:
        for special_price in special_prices:
            shopping_cart = special_price.apply(shopping_cart)
    shopping_cart.price += sum(map(lambda p: p.unit_price, shopping_cart.to_process))
    if discounts:
        shopping_cart.price -= sum(map(lambda d: d.calculate(shopping_cart.to_process), discounts))
    return shopping_cart.price


class TestCheckout(TestCase):

    def test_checkout_no_discounts(self):
        self.assertEqual(130, checkout(ShoppingCart("AAB")))

    def test_2for1_discount(self):
        self.assertEqual(80, checkout(
            ShoppingCart("AAB"),
            discounts = [GroupDiscount(Product.A, 2, 1)]))

    def test_at_least_3_20_percent_discount(self):
        self.assertEqual(144, checkout(
            ShoppingCart("AAAB"),
            discounts = [MinUnitsGlobalDiscount(Product.A, 3, 0.2)]))

    def test_special_price_group_of_three(self):
        self.assertEqual(160, checkout(
            ShoppingCart("AAAB"),
            special_prices = [GroupSpecialPrice(Product.A, 3, 130)]))

    def test_cases_from_kata_definition(self):
        sp = [GroupSpecialPrice(Product.A, 3, 130),
              GroupSpecialPrice(Product.B, 2, 45)]
        self.assertEqual(  0, checkout(ShoppingCart(""), special_prices=sp))
        self.assertEqual( 50, checkout(ShoppingCart("A"), special_prices=sp))
        self.assertEqual( 80, checkout(ShoppingCart("AB"), special_prices=sp))
        self.assertEqual(115, checkout(ShoppingCart("CDBA"), special_prices=sp))

        self.assertEqual(100, checkout(ShoppingCart("AA"), special_prices=sp))
        self.assertEqual(130, checkout(ShoppingCart("AAA"), special_prices=sp))
        self.assertEqual(180, checkout(ShoppingCart("AAAA"), special_prices=sp))
        self.assertEqual(230, checkout(ShoppingCart("AAAAA"), special_prices=sp))
        self.assertEqual(260, checkout(ShoppingCart("AAAAAA"), special_prices=sp))

        self.assertEqual(160, checkout(ShoppingCart("AAAB"), special_prices=sp))
        self.assertEqual(175, checkout(ShoppingCart("AAABB"), special_prices=sp))
        self.assertEqual(190, checkout(ShoppingCart("AAABBD"), special_prices=sp))
        self.assertEqual(190, checkout(ShoppingCart("DABABA"), special_prices=sp))
