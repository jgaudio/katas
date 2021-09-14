from hashlib import md5
from unittest import TestCase
from math import  floor

MD5_HASH_SIZE_BYTES = 16


class BloomFilter:
    def __init__(self, bitmap_size, hash_count=4):
        self._hash_count = hash_count
        self._bitmap_size_bytes = bitmap_size
        self._bitmap = bytearray(self._bitmap_size_bytes)

    def add_word(self, word):
        hashes = self._hash(word)
        for h in hashes:
            self._save_word_hash(h)

    def _is_hash_set(self, the_hash):
        byte_offset, bitmask = self._get_coords(the_hash)
        return (self._bitmap[byte_offset] & bitmask) == bitmask

    def _save_word_hash(self, the_hash):
        byte_offset, bitmask = self._get_coords(the_hash)
        self._bitmap[byte_offset] |= bitmask

    def _get_coords(self, the_hash):
        bit_offset = the_hash % (self._bitmap_size_bytes * 8)
        byte_offset = floor(bit_offset / 8)
        bit_in_byte_pos = bit_offset % 8
        bitmask = 1
        if bit_in_byte_pos:
            for i in range(bit_in_byte_pos):
                bitmask = bitmask << 1
        return byte_offset, bitmask

    def is_present(self, word):
        hashes = self._hash(word)
        return sum(map(lambda h: 1 if self._is_hash_set(h) else 0, hashes)) == len(hashes)

    def _hash(self, word):
        digest = md5(word.encode()).digest()
        digest_size = len(digest)
        hash_size = int(digest_size / self._hash_count)
        return [int.from_bytes(digest[b:b+hash_size], "big") for b in range(0, digest_size, hash_size)]


class BloomFilterTest(TestCase):

    def test_load_full_dictionary(self):
        bloom_filter = BloomFilter(64 * 8 * 1024)
        with open('wordlist.txt', 'r') as dict_file:
            words = dict_file.read()
            for word in words.split('\n'):
                bloom_filter.add_word(word)
                self.assertTrue(bloom_filter.is_present(word))
