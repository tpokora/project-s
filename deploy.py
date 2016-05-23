import subprocess
import os
import time


def separator(msg):
    print('######################################################')
    print('#')
    print('# ' + msg)
    print('#')
    print('######################################################')

env = os.environ.copy()

start_time = time.time()

separator('Building WEBAPP package...')

os.chdir('webapp')
process = subprocess.call('grunt build api-prod', shell=True, stderr=subprocess.PIPE, env=env)

separator('Building WEBAPP package finished')

separator('Building API package...')

os.chdir('..')
process = subprocess.call('mvn package', shell=True, stderr=subprocess.PIPE, env=env)

separator('Building API package finished')

end_time = time.time() - start_time

print('\nBuild time: %s seconds' % end_time)