from enum import Enum
from unittest import TestCase


class Product(Enum):
    BANANA = 0.5
    WATERMELON = 1.5

    def __init__(self, unit_price):
        self._unit_price = unit_price

    def get_unit_price(self):
        return self._unit_price


class Discount:
    def calculate(self, products):
        pass


class GroupDiscount(Discount):
    def __init__(self, product, group_size, free_units):
        self._product = product
        self._group_size = group_size
        self._free_units = free_units

    def calculate(self, products):
        apply_count = len(list(filter(lambda p: p == self._product, products))) / self._group_size
        return apply_count * self._product.get_unit_price() * self._free_units


class MinUnitsGlobalDiscount(Discount):
    def __init__(self, product, min_units, discount_percentage):
        self._product = product
        self._min_units = min_units
        self._discount_percentage = discount_percentage

    def calculate(self, products):
        product_count = len(list(filter(lambda p: p == self._product, products)))
        if product_count >= self._min_units:
            return sum(map(lambda p: p.get_unit_price(), products)) * self._discount_percentage


def checkout(products, discounts=None):
    price = sum(map(lambda p: p.get_unit_price(), products))
    if discounts:
        price -= sum(map(lambda d: d.calculate(products), discounts))
    return price


class TestCheckout(TestCase):

    def test_checkout_no_discounts(self):
        self.assertEqual(2.5, checkout(
            [Product.BANANA, Product.BANANA, Product.WATERMELON]))

    def test_2for1_discount(self):
        self.assertEqual(2, checkout(
            [Product.BANANA, Product.BANANA, Product.WATERMELON],
            [GroupDiscount(Product.BANANA, 2, 1)]))

    def test_at_least_3_20_percent_discount(self):
        self.assertEqual(2.4, checkout(
            [Product.BANANA, Product.BANANA, Product.BANANA, Product.WATERMELON],
            [MinUnitsGlobalDiscount(Product.BANANA, 3, 0.2)]))
