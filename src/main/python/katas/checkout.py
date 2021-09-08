from enum import Enum
from unittest import TestCase


class Product(Enum):
    A = 50
    B = 30
    C = 20
    D = 15

    def __init__(self, unit_price):
        self.unit_price = unit_price


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


def checkout(products, discounts=None):
    price = sum(map(lambda p: p.unit_price, products))
    if discounts:
        price -= sum(map(lambda d: d.calculate(products), discounts))
    return price


class TestCheckout(TestCase):

    def test_checkout_no_discounts(self):
        self.assertEqual(130, checkout(
            [Product.A, Product.A, Product.B]))

    def test_2for1_discount(self):
        self.assertEqual(80, checkout(
            [Product.A, Product.A, Product.B],
            [GroupDiscount(Product.A, 2, 1)]))

    def test_at_least_3_20_percent_discount(self):
        self.assertEqual(144, checkout(
            [Product.A, Product.A, Product.A, Product.B],
            [MinUnitsGlobalDiscount(Product.A, 3, 0.2)]))
