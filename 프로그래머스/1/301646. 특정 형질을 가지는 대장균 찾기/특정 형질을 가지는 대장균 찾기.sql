select count(ID) as COUNT 
from ECOLI_DATA 
where (GENOTYPE & 1 = 1 
OR GENOTYPE & (1<<2) >= (1<<2)) 
AND GENOTYPE & (1<<1) < (1<<1);